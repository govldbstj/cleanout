import React from 'react';
import styled from 'styled-components/native';
import { StatusBar } from 'expo-status-bar';

const Container = styled.View`
flex: 1;
justify-content: center;
align-items: center;
`;
const StyledText = styled.Text`
font-size: 30px;
margin-bottom : 10px;
`;

const Item = ( {route} ) => {
    return(
        <Container>
            <StyledText>Item</StyledText>
            <StyledText>ID: {route.params.id}</StyledText>
            <StyledText>Name: {route.params.name}</StyledText>
        </Container>
    )
};

export default Item;