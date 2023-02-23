import React from 'react';
import styled from 'styled-components/native';
import StatusRow from '../components/molecules/StatusRow';
import { getReservations } from '../controllers/ReservationController';
import { Pressable, ScrollView } from 'react-native';
import colors from '../styles/colors';
import LoadingContext from '../context/Loading';

const Description = styled.Text`
    margin-top: 50px;
    margin-bottom: 50px;
    font-size: 13px;
    color: ${colors.disabled};
    flex: 1;
`;

//예약 목록
const Reservation = ({ navigation }) => {
    const header = ['신청일', '품목', '수거 상태'];
    const [data, setData] = React.useState([]);
    const { setIsLoading } = React.useContext(LoadingContext);

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
        <ScrollView contentContainerStyle={{ alignItems: 'center', marginTop: '2%' }}>
            <StatusRow data={header} isHeader={true} />
            {data.map((data, index) => (
                <Pressable key={index} onPress={() => navigation.push('ReservationDetail', { id: data.id })}>
                    <StatusRow data={[data.date, data.name, data.status]} />
                </Pressable>
            ))}
            <Description>각 항목을 눌러 자세한 내용을 확인하세요.</Description>
        </ScrollView>
    );
};

export default Reservation;
