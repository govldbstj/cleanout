import React from 'react';
import styled from 'styled-components/native';

const Container = styled.View`
align-items: center;
`;
const StyledText = styled.Text`
font-size : 30px;
margin-bottom: 10px;
`;
const MidText = styled.Text`
font-size : 22px;
margin-bottom: 10px;
`;
const DetailText = styled.Text`
font-size : 15px;
margin-bottom: 10px;
`;

//예약 목록
const Reservation = ({ navigation }) => {
    return(
        <Container>
            <StyledText>reservation page</StyledText>
            <MidText>22/01/01 | 냉장고 | 수거 완료</MidText>
            <DetailText>타임스탬프  | 품목 | 현황</DetailText>
        </Container>
    )
}

export default Reservation;