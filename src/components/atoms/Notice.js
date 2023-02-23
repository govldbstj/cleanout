import React from 'react';
import colors from '../../styles/colors';
import styled from 'styled-components/native';

export default Notice = ({ ...props }) => {
    return (
        <Container {...props}>
            <Title>📌 주의 사항</Title>
            <Content>1. 이미지 등록시 품목 확인을 위해 쓰레기 전체가 명확하게 찍힌 이미지를 선택해주세요.</Content>
            <Content>2. 이미지를 여러장 업로드하면 이미지 하나당 쓰레기 한 개가 등록됩니다. 같은 쓰레기의 사진을 여러개 등록하지 마세요.</Content>
            <Content>3. 쓰레기는 등록 후 수거자가 매칭되어야 수거가 진행되며, 최대 3~4일이 소요될 수 있습니다. 자세한 내용은 예약 현황을 참고해주세요.</Content>
            <Content>4. 등록한 이미지와 실제 쓰레기가 다른 경우 현장에서 수거가 거부될 수 있습니다.</Content>
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
