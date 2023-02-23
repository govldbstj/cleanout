import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import StackNavigation from './navigations/Stack';
import { AddressProvider } from './context/Address';
import { LoginProvider } from './context/Login';
import { LoadingProvider } from './context/Loading';

const App = () => {
    return (
        <LoginProvider>
            <AddressProvider>
                <LoadingProvider>
                    <NavigationContainer>
                        <StackNavigation />
                    </NavigationContainer>
                </LoadingProvider>
            </AddressProvider>
        </LoginProvider>
    );
};

export default App;
