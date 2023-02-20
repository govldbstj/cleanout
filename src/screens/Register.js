import React, { useState, useEffect } from 'react';
import styled from 'styled-components/native';
import FormTextInput from '../components/molecules/FormTextInput';
import ScrollImageList from '../components/molecules/ScrollImageList';
import Button from '../components/atoms/Button';
import Notice from '../components/atoms/Notice';
import colors from '../styles/colors';
import { ScrollView, ActivityIndicator } from 'react-native';
import * as ImagePicker from 'expo-image-picker';
import { getUserInfo } from '../controllers/LoginController';
import { uploadWasteImage } from '../controllers/TrashRegisterController';
import LoadingContext from '../context/Loading';

const AlignRightContainer = styled.View`
    width: 90%;
    align-items: flex-end;
    margintop: 20px;
`;

const Spacer = styled.View`
    height: 50px;
`;

const Center = styled.View`
    flex: 1;
    justify-content: center;
    align-items: center;
`;

// 이미지 선택이 안되는 버그 => IOS Simulator에서만 발생하는 문제로, 다른 기기로 테스트해주세요!
const Register = ({ navigation }) => {
    const [images, setImages] = useState([]);
    const [userData, setUserData] = useState({
        name: '',
        address: '',
        isLoading: true,
    });
    const { setIsLoading } = React.useContext(LoadingContext);

    useEffect(() => {
        setIsLoading(true);
        getUserInfo().then((result) => {
            if (result.isSuccess()) {
                const data = result.tryGetValue();
                setUserData({
                    ...data,
                    isLoading: false,
                });
            } else {
                alert('회원 정보를 불러오는데 실패했습니다.');
                navigation.popToTop();
            }
            setIsLoading(false);
        });
    }, []);

    const submit = async () => {
        setIsLoading(true);
        const imageResult = await uploadWasteImage(images);

        if (imageResult.isSuccess()) {
            alert('등록에 성공하였습니다.');
            navigation.popToTop();
        } else {
            if (imageResult.isInAppFailure()) {
                alert(imageResult.tryGetErrorMessage());
            } else {
                alert('등록에 실패하였습니다. 다시 시도해주세요.');
            }
        }
        setIsLoading(false);
    };

    return userData.isLoading ? (
        <Center>
            <ActivityIndicator size="large" color={colors.primary}></ActivityIndicator>
        </Center>
    ) : (
        <ScrollView contentContainerStyle={{ alignItems: 'center', marginTop: '5%' }}>
            <FormTextInput label="이름" value={userData.name} disabled={true} />
            <FormTextInput label="주소" disabled={true} value={userData.address} />
            <AlignRightContainer>
                <Button
                    title="📩 이미지 불러오기"
                    onPress={async () => {
                        const images = await getImageSelection();
                        if (images !== null) setImages(images);
                    }}
                />
                <ScrollImageList sources={images} />
            </AlignRightContainer>
            <Notice />
            <Button title="등록하기" onPress={() => submit()} />
            <Spacer />
        </ScrollView>
    );
};

/**
 * 사용자에게 이미지 선택을 받는 함수
 * @returns {Promise<string[]> | null} 선택된 이미지의 uri 리스트
 */
async function getImageSelection() {
    const { status } = await ImagePicker.requestCameraPermissionsAsync();

    if (status !== 'granted') {
        alert('게시글을 업로드하려면 사진첩 권한이 필요합니다.');
        return null;
    }

    let imageData = await ImagePicker.launchImageLibraryAsync({
        mediaTypes: ImagePicker.MediaTypeOptions.Images,
        allowsMultipleSelection: false, // 다중 선택 가능 여부
        allowsEditing: false, // 사진 촬영 후 편집 화면 보여줄 지 여부
        aspect: [1, 1], // 사진의 비율
        quality: 1, // 사진의 용량
        selectionLimit: 1, // 최대 선택 가능한 사진 개수
    });

    if (imageData.cancelled) {
        return null;
    }

    const uriList = !!imageData.uri ? [imageData.uri] : imageData.selected.map((item) => item.uri);
    return uriList;
}

export default Register;
