import React from 'react';
import colors from '../../styles/colors';
import styled from 'styled-components/native';

export default Notice = ({ ...props }) => {
    return (
        <Container {...props}>
            <Title>📌 주의 사항</Title>
            <Content>1. 품목 전체가 명확하게 찍힌 이미지를 선택해주세요.</Content>
            <Content>2. 같은 쓰레기의 사진을 여러 장 등록하지 마세요.</Content>
            <Content>3. 수거자가 매칭된 후 진행되며 최대 3~4일 소요됩니다.</Content>
            <Content>4. 등록한 이미지와 실물이 다른 경우, 현장에서 수거가 거부될 수 있습니다.</Content>
            <Content>5. 자세한 내용은 예약 현황을 참고해주세요.</Content>
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
