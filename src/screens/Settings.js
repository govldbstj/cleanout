import React from 'react';
import {Button} from 'react-native';
import styled from 'styled-components/native';

const Container = styled.View`
flex: 1;
justify-content: center;
align-items: center;
`;

const StyledText = styled.Text`
font-size: 30px;
margin-bottom: 10px;
`;

const items = [
    {_id: 1, name: '기록'},
    {_id: 2, name: '즐겨찾기'}, 
    {_id: 3, name: '알림'}
];

const Settings = ({navigation}) => {
    const _onPress = item => {
        navigation.navigate('Item', { id: item._id, name: item.name });
    };

    return(
        <Container>
            <StyledText>Settings</StyledText>
            {items.map(item => (
                <Button
                key = {item._id}
                title = {item.name}
                onPress = {()=> _onPress(item)}
                />
            ))}
        </Container>
    )
}

export default Settings;