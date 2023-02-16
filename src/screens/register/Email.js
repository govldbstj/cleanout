import React from 'react';
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
        </Container>
    );
};

export default Email;
