import React from 'react';
import styled from 'styled-components/native';

/**
 * 기본 텍스트 atom (기본 폰트 사이즈 15px)
 * @param {*} props style 등 다양한 props
 */
export default Label = ({children, ...props}) => {
    return <StyledText {...props}>{children}</StyledText>;
};

const StyledText = styled.Text`
    font-size: 15px;
`;
