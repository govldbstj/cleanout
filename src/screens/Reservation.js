import React from 'react';
import styled from 'styled-components/native';
import StatusRow from '../components/molecules/StatusRow';

const Container = styled.View`
    align-items: center;
`;
const StyledText = styled.Text`
    font-size: 30px;
    margin-bottom: 10px;
`;

//예약 목록
const Reservation = ({ navigation }) => {
    const header = ['신청일', '품목', '수거 상태'];
    const data = [
        ['2023-01-01', '냉장고', '수거 완료'],
        ['2023-01-04', '에어컨', '1시간 후 수거'],
    ];

    return (
        <Container>
            <StyledText>reservation page</StyledText>
            <StatusRow data={header} isHeader={true} />
            {data.map((data, index) => (
                <StatusRow key={index} data={data} />
            ))}
        </Container>
    );
};

export default Reservation;
