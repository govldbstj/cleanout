import React, { createContext, useState } from 'react';

const LoadingContext = createContext({
    isLoading: false,
    setIsLoading: () => {},
});

const LoadingProvider = ({ children }) => {
    const [isLoading, setIsLoading] = useState(false);

    const value = { isLoading, setIsLoading };
    return <LoadingContext.Provider value={value}>{children}</LoadingContext.Provider>;
};

const LoadingConsumer = LoadingContext.Consumer;

export { LoadingProvider, LoadingConsumer };
export default LoadingContext;
