import React from 'react';
import {Button} from 'react-native';
import styled from 'styled-components/native';

const Container = styled.View`
align-items: center;
`;
const StyledText = styled.Text`
font-size : 30px;
margin-bottom: 10px;
`;
const DetailText = styled.Text`
font-size : 15px;
margin-bottom: 10px;
`;

const Register = ({ navigation }) => {
    return(
        <Container>
            <StyledText>register page</StyledText>
            <DetailText>이름 : input text</DetailText>
            <DetailText>주소 : 주소 입력 api 불러오기</DetailText>
            <DetailText>이미지 넣기 버튼 -> 이미지 입력 api</DetailText>
            <DetailText>사진 불러오기 -> 이미지 미리보기</DetailText>
            <DetailText>주의사항 : 단순컴포넌트 or 사진</DetailText>
            <DetailText>등록 버튼</DetailText>
        </Container>
    )
}

export default Register;