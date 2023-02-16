import React from 'react';
import styled from 'styled-components/native';
import FormTextInput from '../components/molecules/FormTextInput';
import Button from '../components/atoms/Button';
import Notice from '../components/atoms/Notice';

const Container = styled.View`
    align-items: center;
`;
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
    return (
        <Container>
            <StyledText>register page</StyledText>
            <FormTextInput label="이름" placeholder="이름을 입력하세요." />
            <FormTextInput
                label="주소"
                placeholder="주소를 입력하세요."
                buttonLabel="검색"
                onButtonPress={() => console.log('주소 검색 기능 필요')}
            />
            <Button title="이미지 넣기" />
            <StyledImage source={{uri: "https://dummyimage.com/600x400/000/fff.png&text=Image+Preview"}}/>
            <Notice/>
            <Button title="등록하기" />
        </Container>
    );
};

export default Register;
