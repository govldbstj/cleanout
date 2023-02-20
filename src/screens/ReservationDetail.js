import React, { useState, useEffect } from 'react';
import styled from 'styled-components/native';
import FormTextInput from '../components/molecules/FormTextInput';
import Notice from '../components/atoms/Notice';
import colors from '../styles/colors';
import { ScrollView, ActivityIndicator } from 'react-native';
import { getReservation } from '../controllers/ReservationController';
import LoadingContext from '../context/Loading';

const Spacer = styled.View`
    height: 50px;
`;

const Center = styled.View`
    flex: 1;
    justify-content: center;
    align-items: center;
`;


const ReservationDetail = ({ navigation, route }) => {
    const id = route.params.id;

    if (id === undefined || id === null) {
        alert('잘못된 접근입니다.');
        navigation.popToTop();
    }

    const [trashData, setTrashData] = useState({
        name: '',
        address: '',
        wasteName: '',
        price: '',
        status: '',
        image: '',
    });
    const { setIsLoading } = React.useContext(LoadingContext);

    useEffect(() => {
        setIsLoading(true);
        getReservation(id).then((result) => {
            if (result.isSuccess()) {
                const trashData = result.tryGetValue();
                setTrashData({
                    ...trashData[0],
                });
            } else {
                alert('예약 정보를 불러오는데 실패했습니다.');
                navigation.popToTop();
            }
            setIsLoading(false);
        });
    }, []);

    return (
        <ScrollView contentContainerStyle={{ alignItems: 'center', marginTop: '5%' }}>
            <FormTextInput label="이름" value={trashData.name} disabled={true} />
            <FormTextInput label="주소" disabled={true} value={trashData.address} />
            <FormTextInput label="쓰레기 종류" disabled={true} value={trashData.wasteName} />
            <FormTextInput label="가격" disabled={true} value={trashData.price} />
            <FormTextInput label="수거 상태" disabled={true} value={trashData.status} />
            <ScrollImageList sources={[trashData.image]} />
            <Notice />
            <Spacer />
        </ScrollView>
    );
};

export default ReservationDetail;
