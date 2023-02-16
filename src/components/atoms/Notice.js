import React from 'react';
import colors from '../../styles/colors';
import styled from 'styled-components/native';

export default Notice = ({ ...props }) => {
    return (
        <Container {...props}>
            <Title>📌 주의 사항</Title>
            <Content>1. 이것 저것을 주의하세요.</Content>
            <Content>2. 이것 저것을 주의하세요.</Content>
            <Content>3. 이것 저것을 주의하세요.</Content>
        </Container>
    );
};

const Container = styled.View`
    width: 90%;
    padding: 10px;
    background-color: ${colors.secondary};
    border-radius: 10px;
    margin: 10px 0;
    padding: 10px;
`;

const Title = styled.Text`
    font-size: 15px;
    font-weight: bold;
    margin-bottom: 10px;
`;

const Content = styled.Text`
    margin: 5px 0;
`;
