import React, { useState, useEffect } from 'react';
import styled from 'styled-components/native';
import FormTextInput from '../components/molecules/FormTextInput';
import Notice from '../components/atoms/Notice';
import colors from '../styles/colors';
import { ScrollView, ActivityIndicator } from 'react-native';
import { getUserInfo } from '../controllers/LoginController';
import { getReservation } from '../controllers/ReservationController';

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

    const [userData, setUserData] = useState({
        name: '',
        address: '',
        isLoading: true,
    });
    const [trashData, setTrashData] = useState({

    });

    useEffect(() => {
        getUserInfo().then((result) => {
            if (result.isSuccess()) {
                const data = result.tryGetValue();
                setUserData({
                    ...data,
                    isLoading: false,
                });
            } else {
                alert('회원 정보를 불러오는데 실패했습니다.');
                navigation.popToTop();
            }
        });

        getReservation(id).then((result) => {
            if (result.isSuccess()) {
                const data = result.tryGetValue();
                console.log(data);
                setTrashData({
                    ...data[0],
                    isLoading: false,
                });
            } else {
                alert('예약 정보를 불러오는데 실패했습니다.');
                navigation.popToTop();
            }
        });
    }, []);

    return userData.isLoading || trashData.isLoading ? (
        <Center>
            <ActivityIndicator size="large" color={colors.primary}></ActivityIndicator>
        </Center>
    ) : (
        <ScrollView contentContainerStyle={{ alignItems: 'center', marginTop: '5%' }}>
            <FormTextInput label="이름" value={userData.name} disabled={true} />
            <FormTextInput label="주소" disabled={true} value={userData.address} />
            <FormTextInput label="쓰레기 종류" disabled={true} value={trashData.wasteName} />
            <FormTextInput label="가격" disabled={true} value={trashData.price} />
            <FormTextInput label="수거 상태" disabled={true} value={trashData.status} />
            <Notice />
            <Spacer />
        </ScrollView>
    );
};

export default ReservationDetail;
