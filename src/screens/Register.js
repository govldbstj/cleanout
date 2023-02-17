import React, {useState, useContext} from 'react';
import styled from 'styled-components/native';
import FormTextInput from '../components/molecules/FormTextInput';
import Button from '../components/atoms/Button';
import Notice from '../components/atoms/Notice';
import {ScrollView} from 'react-native';
import AddressContext, { AddressConsumer } from '../context/Address';

const StyledText = styled.Text`
    font-size: 30px;
    margin-bottom: 10px;
`;
const StyledImage = styled.Image`
    width: 300px;
    height: 200px;
    object-fit: cover;
`;
const Register = ({ navigation }) => {
    // 각각의 FormItem에 disabled=true를 주면 해당 컴포넌트를 비활성화할 수 있습니다. (2-3-1뷰에서 사용 가능)
    const [name, setName] = useState('');
    const [info, setInfo] = useState('');

    const { address } = useContext(AddressContext);

    console.log("카카오 주소 ", address);

    //상세주소는 따로 추가해주세요!

    return (
        <ScrollView contentContainerStyle={{ alignItems: "center" }}>
            <StyledText>register page</StyledText>
            <FormTextInput 
                label="이름" 
                placeholder="이름을 입력하세요." 
                onTextChangeListener={(text) => {
                    setName(text)
                }}
                />
            <Button title="주소 검색" onPress= {()=> navigation.navigate('Address')}/>
            <FormTextInput 
                label="상세 주소" 
                placeholder="상세 주소를 입력하세요." 
                onTextChangeListener={(text) => {
                    setInfo(text)
                }}
            />
            <Button title="이미지 넣기" onPress={() => console.log('이미지 넣기 기능 필요')}/>
            <StyledImage source={{uri: "https://dummyimage.com/600x400/000/fff.png&text=Image+Preview"}}/>
            <Notice/>
            <Button title="등록하기" onPress={() => { setName(name); console.log("name is", name, "address is ", address+' '+info);}}/>
        </ScrollView>
    );
};

export default Register;
