import React from 'react';
import styled from 'styled-components/native';
import StatusRow from '../components/molecules/StatusRow';
import { getReservations } from '../controllers/ReservationController';
import { Pressable } from 'react-native';
import LoadingContext from '../context/Loading';

const Container = styled.View`
    margin-top: 10px;
    align-items: center;
`;

//예약 목록
const Reservation = ({ navigation }) => {
    const header = ['신청일', '품목', '수거 상태'];
    const [data, setData] = React.useState([]);
    const { setIsLoading } = React.useContext(LoadingContext);

    // 날짜 품목 시간

    React.useEffect(() => {
        setIsLoading(true);
        getReservations().then((result) => {
            if (result.isSuccess()) {
                setData(result.tryGetValue());
            } else {
                alert('예약 목록을 불러오는데 실패했습니다.');
                navigation.popToTop();
            }
            setIsLoading(false);
        });
    }, []);

    return (
        <Container>
            <StatusRow data={header} isHeader={true} />
            {data.map((data, index) => (
                <Pressable key={index} onPress={() => navigation.push('ReservationDetail', { id: data.id })}>
                    <StatusRow data={[data.date, data.name, data.status]} />
                </Pressable>
            ))}
        </Container>
    );
};

export default Reservation;
