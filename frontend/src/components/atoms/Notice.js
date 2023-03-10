import React from 'react';
import colors from '../../styles/colors';
import styled from 'styled-components/native';

export default Notice = ({ ...props }) => {
    return (
        <Container {...props}>
            <Title>๐ ์ฃผ์ ์ฌํญ</Title>
            <Content>1. ์ด๋ฏธ์ง ๋ฑ๋ก์ ํ๋ชฉ ํ์ธ์ ์ํด ์ฐ๋ ๊ธฐ ์ ์ฒด๊ฐ ๋ชํํ๊ฒ ์ฐํ ์ด๋ฏธ์ง๋ฅผ ์ ํํด์ฃผ์ธ์.</Content>
            <Content>2. ์ด๋ฏธ์ง๋ฅผ ์ฌ๋ฌ์ฅ ์๋ก๋ํ๋ฉด ์ด๋ฏธ์ง ํ๋๋น ์ฐ๋ ๊ธฐ ํ ๊ฐ๊ฐ ๋ฑ๋ก๋ฉ๋๋ค. ๊ฐ์ ์ฐ๋ ๊ธฐ์ ์ฌ์ง์ ์ฌ๋ฌ๊ฐ ๋ฑ๋กํ์ง ๋ง์ธ์.</Content>
            <Content>3. ์ฐ๋ ๊ธฐ๋ ๋ฑ๋ก ํ ์๊ฑฐ์๊ฐ ๋งค์นญ๋์ด์ผ ์๊ฑฐ๊ฐ ์งํ๋๋ฉฐ, ์ต๋ 3~4์ผ์ด ์์๋  ์ ์์ต๋๋ค. ์์ธํ ๋ด์ฉ์ ์์ฝ ํํฉ์ ์ฐธ๊ณ ํด์ฃผ์ธ์.</Content>
            <Content>4. ๋ฑ๋กํ ์ด๋ฏธ์ง์ ์ค์  ์ฐ๋ ๊ธฐ๊ฐ ๋ค๋ฅธ ๊ฒฝ์ฐ ํ์ฅ์์ ์๊ฑฐ๊ฐ ๊ฑฐ๋ถ๋  ์ ์์ต๋๋ค.</Content>
        </Container>
    );
};

const Container = styled.View`
    width: 90%;
    padding: 10px;
    background-color: ${colors.secondary};
    border-radius: 10px;
    margin: 10px 0;
`;

const Title = styled.Text`
    font-size: 15px;
    font-weight: bold;
    margin-bottom: 10px;
`;

const Content = styled.Text`
    margin: 5px 0;
`;
