import React, { useState, useContext } from 'react';
import Postcode from '@actbase/react-daum-postcode';
import AddressContext, { AddressConsumer } from '../context/Address';

const Address = ({ navigation }) => {
    const [address, setAddress] = useState('');
    const { dispatch } = useContext(AddressContext);

    const getAddressData = (data) => {
        let defaultAddress = ''; // 기본주소
        setAddress(data.address);
        dispatch(data.address);

        navigation.goBack();
    };

    return (
        <Postcode
            style={{ flex: 1 }}
            jsOptions={{ animation: true }}
            onSelected={(data) => getAddressData(data)}
            onError={(error) => {
                throw new Error('Function not implemented.');
            }}
        />
    );
};

export default Address;
