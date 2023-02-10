import React from 'react';
import {Button} from 'react-native';
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

const Kakao = ({navigation}) => {

    return(
        <Container>
            <StyledText>Kakao Login page</StyledText>
        </Container>
    )
}

export default Kakao;