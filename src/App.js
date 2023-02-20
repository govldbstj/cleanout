import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import StackNavigation from './navigations/Stack';
import { AddressProvider } from './context/Address';
import { LoginProvider } from './context/Login';

const App = () => {
    return (
        <LoginProvider>
            <AddressProvider>
                <NavigationContainer>
                    <StackNavigation />
                </NavigationContainer>
            </AddressProvider>
        </LoginProvider>
    );
};

export default App;
