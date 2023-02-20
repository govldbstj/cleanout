import React, { createContext, useState } from 'react';

const LoginContext = createContext({
    isLogin: false,
    dispatch: () => {},
});

const LoginProvider = ({ children }) => {
    const [isLogin, setIsLogin] = useState('');

    const value = { isLogin, dispatch: setIsLogin };
    return <LoginContext.Provider value={value}>{children}</LoginContext.Provider>;
};

const LoginConsumer = LoginContext.Consumer;

export { LoginProvider, LoginConsumer };
export default LoginContext;
