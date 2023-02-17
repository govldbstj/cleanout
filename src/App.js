import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import StackNavigation from './navigations/Stack';
import { AddressProvider } from './context/Address';

const App = () => {
    return(
        <AddressProvider>
        <NavigationContainer>
            <StackNavigation/>
        </NavigationContainer>
        </AddressProvider>
    )
}

export default App;
