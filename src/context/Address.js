import React, { createContext, useState } from 'react';

const AddressContext = createContext({
  Address: '',
  dispatch: () => {},
});

const AddressProvider = ({ children }) => {
  const [address, setAddress] = useState('');

  const value = { address , dispatch : setAddress };
  return <AddressContext.Provider value={value}>{children}</AddressContext.Provider>;
};

const AddressConsumer = AddressContext.Consumer;

export { AddressProvider, AddressConsumer };
export default AddressContext;