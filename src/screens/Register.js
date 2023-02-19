import React, { useState, useContext } from 'react';
import styled from 'styled-components/native';
import FormTextInput from '../components/molecules/FormTextInput';
import ScrollImageList from '../components/molecules/ScrollImageList';
import Button from '../components/atoms/Button';
import Notice from '../components/atoms/Notice';
import { ScrollView } from 'react-native';
import AddressContext, { AddressConsumer } from '../context/Address';
import * as ImagePicker from 'expo-image-picker';
import { registerWaste, uploadWasteImage } from '../controllers/TrashRegisterController';

const AlignRightContainer = styled.View`
    width: 90%;
    align-items: flex-end;
    margin: 20px 0;
`;

const Spacer = styled.View`
    height: 50px;
`;

// 이미지 선택이 안되는 버그 => IOS Simulator에서만 발생하는 문제로, 다른 기기로 테스트해주세요!
const Register = ({ navigation }) => {
    const [name, setName] = useState('');
    const [info, setInfo] = useState('');
    const [images, setImages] = useState([]);

    const { address } = useContext(AddressContext);

    return (
        <ScrollView contentContainerStyle={{ alignItems: 'center', marginTop: '5%' }}>
            <FormTextInput
                label="이름"
                placeholder="이름을 입력하세요."
                onTextChangeListener={(text) => {
                    setName(text);
                }}
            />
            <FormTextInput
                label="주소"
                placeholder="검색 버튼을 눌러 주소를 입력하세요."
                disabled={true}
                value={address}
                buttonLabel="검색"
                onButtonPress={() => {
                    navigation.navigate('Address');
                }}
            />
            <FormTextInput
                label="상세 주소"
                placeholder="상세 주소를 입력하세요."
                onTextChangeListener={(text) => {
                    setInfo(text);
                }}
            />
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
            <Button
                title="등록하기"
                onPress={() => {
                    submit(name, address, info, images, () => {
                        navigation.popToTop();
                    });
                }}
            />
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
        allowsMultipleSelection: true, // 다중 선택 가능 여부
        allowsEditing: false, // 사진 촬영 후 편집 화면 보여줄 지 여부
        aspect: [1, 1], // 사진의 비율
        quality: 1, // 사진의 용량
        selectionLimit: 5, // 최대 선택 가능한 사진 개수
    });

    if (imageData.cancelled) {
        return null;
    }

    console.log(imageData);

    const uriList = !!imageData.uri ? [imageData.uri] : imageData.selected.map((item) => item.uri);
    return uriList;
}

/**
 * 사용자가 입력한 정보를 서버로 전송하는 함수
 * @param {string} name 이름
 * @param {string} address 주소
 * @param {string} info 상세 주소
 * @param {string[]} images 이미지 uri 리스트
 * @param {function} onSuccess 성공 시 실행할 함수
 */
async function submit(name, address, info, images, onSuccess) {
    /* 아마도 최종 코드?
    const result = await registerWaste(name, address, info);

    if (result.isSuccess()) {
        const imageResult = await uploadWasteImage(images);

        if (imageResult.isSuccess()) {
            alert('등록에 성공하였습니다.');
            onSuccess();
        } else {
            if (imageResult.isInAppFailure()) {
                alert(imageResult.tryGetErrorMessage());
                return;
            }
            alert('등록에 실패하였습니다. 다시 시도해주세요.');
            console.log('RegisterImageError', imageResult.tryGetErrorCode(), imageResult.tryGetErrorMessage());
        }
    } else {
        if (result.isInAppFailure()) {
            alert(result.tryGetErrorMessage());
            return;
        }
        alert('등록에 실패하였습니다. 다시 시도해주세요.');
        console.log('RegisterError', result.tryGetErrorCode(), result.tryGetErrorMessage());
    }
    */

    // 이미지만 전송하는 코드
    const imageResult = await uploadWasteImage(images);

    if (imageResult.isSuccess()) {
        alert('등록에 성공하였습니다.');
        onSuccess();
    } else {
        if (imageResult.isInAppFailure()) {
            alert(imageResult.tryGetErrorMessage());
            return;
        }
        alert('등록에 실패하였습니다. 다시 시도해주세요.');
        console.log('RegisterImageError', imageResult.tryGetErrorCode(), imageResult.tryGetErrorMessage());
    }
}

export default Register;
