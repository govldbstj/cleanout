import React, { useState, useContext } from 'react';
import styled from 'styled-components/native';
import FormTextInput from '../components/molecules/FormTextInput';
import ScrollImageList from '../components/molecules/ScrollImageList';
import Button from '../components/atoms/Button';
import Notice from '../components/atoms/Notice';
import { ScrollView } from 'react-native';
import AddressContext, { AddressConsumer } from '../context/Address';
import * as ImagePicker from 'expo-image-picker';

const StyledText = styled.Text`
    font-size: 30px;
    margin-bottom: 10px;
`;

const Register = ({ navigation }) => {
    const [name, setName] = useState('');
    const [info, setInfo] = useState('');
    const [images, setImages] = useState([]);

    const { address } = useContext(AddressContext);

    const onSubmit = () => {
        // 이름, 주소, 상세 주소 전송

        // 이미지 전송

    };

    return (
        <ScrollView contentContainerStyle={{ alignItems: 'center' }}>
            <StyledText>register page</StyledText>
            <FormTextInput
                label="이름"
                placeholder="이름을 입력하세요."
                onTextChangeListener={(text) => {
                    setName(text);
                }}
            />
            <FormTextInput
                label="주소"
                placeholder="주소를 입력하세요."
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
            <Button
                title="이미지 넣기"
                onPress={() => {
                    setImages(getImageSelection());
                }}
            />
            <ScrollImageList sources={images} />
            <Notice />
            <Button
                title="등록하기"
                onPress={() => {
                    onSubmit();
                }}
            />
        </ScrollView>
    );
};

/**
 * 사용자에게 이미지 선택을 받는 함수
 * @returns {Promise<string[]>} 선택된 이미지의 uri 리스트
 */
async function getImageSelection() {
    const { status } = await ImagePicker.requestCameraPermissionsAsync();

    if (status !== 'granted') {
        alert('게시글을 업로드하려면 사진첩 권한이 필요합니다.');
        return;
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
        return;
    }

    console.log(imageData);

    const uriList = !!imageData.uri ? [imageData.uri] : imageData.selected.map((item) => item.uri);
    return uriList;
}

export default Register;
