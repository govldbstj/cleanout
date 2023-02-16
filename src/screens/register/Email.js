import React from 'react';
import TextInput from '../../components/atoms/TextInput';
import Label from '../../components/atoms/Label';
import TextButton from '../../components/atoms/TextButton';
import styled from 'styled-components/native';

const Container = styled.View`
    flex: 1;
    justify-content: center;
    align-items: center;
`;

const StyledText = styled.Text`
    font-size: 30px;
    margin-bottom: 10px;
`;

const Email = ({ navigation }) => {
    return (
        <Container>
            <StyledText>Email Login page</StyledText>
            <Label>응애</Label>
            <TextInput placeholder="이메일을 입력해주세요."></TextInput>
            <TextButton title="테스트" onPress={() => console.log('OK')}></TextButton>
        </Container>
    );
};

export default Email;
