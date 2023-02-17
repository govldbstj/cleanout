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
    // 각각의 FormItem에 disabled=true를 주면 해당 컴포넌트를 비활성화할 수 있습니다. (2-3-1뷰에서 사용 가능)
    const [name, setName] = useState('');
    const [info, setInfo] = useState('');

    const { address } = useContext(AddressContext);

    console.log('카카오 주소 ', address);

    //상세주소는 따로 추가해주세요!

    const textImages = [
        { uri: 'https://dummyimage.com/300x300/000/fff.png&text=Image+Preview+1' },
        { uri: 'https://dummyimage.com/300x300/000/fff.png&text=Image+Preview+2' },
        { uri: 'https://dummyimage.com/300x300/000/fff.png&text=Image+Preview+3' },
        { uri: 'https://dummyimage.com/300x300/000/fff.png&text=Image+Preview+4' },
    ];

    /* <ScrollImageList sources={textImages} /> */
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
            <Button title="주소 검색" onPress={() => navigation.navigate('Address')} />
            <FormTextInput
                label="상세 주소"
                placeholder="상세 주소를 입력하세요."
                onTextChangeListener={(text) => {
                    setInfo(text);
                }}
            />
            <Button title="이미지 넣기" onPress={() => uploadImage()} />
            <Notice />
            <Button
                title="등록하기"
                onPress={() => {
                    setName(name);
                    console.log('name is', name, 'address is ', address + ' ' + info);
                }}
            />
        </ScrollView>
    );
};

const uploadImage = async () => {
    const { status } = await ImagePicker.requestCameraPermissionsAsync();

    if (status !== 'granted') {
        alert('게시글을 업로드하려면 사진첩 권한이 필요합니다.');
        return;
    }

    let imageData = await ImagePicker.launchImageLibraryAsync({
        mediaTypes: ImagePicker.MediaTypeOptions.Images,
        allowsEditing: false, // 사진 촬영 후 편집 화면 보여줄 지 여부
        aspect: [1, 1], // 사진의 비율
        quality: 1, // 사진의 용량
        base64: true, // base64로 인코딩된 텍스트 필요한 지 여부
    });

    // imageData.base64로 base64 인코딩된 텍스트를 받을 수 있습니다.
    console.log(imageData.base64);
};

export default Register;
