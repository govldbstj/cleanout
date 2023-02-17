import React from 'react';
import colors from '../../styles/colors';
import styled from 'styled-components/native';
import Label from '../atoms/Label';

/**
 * 여러개의 라벨을 가로로 나열하는 컴포넌트
 * @param {string[]} data 라벨의 텍스트 배열
 * @param {boolean?} isHeader 헤더인지 여부(기본: false)
 */
export default StatusRow = (props) => {
    const { data, isHeader = false, ...others } = props;

    return (
        <Container color={isHeader ? colors.primary : colors.secondary} {...others}>
            {data.map((text, index) => (
                <>
                    {!!index && <Divider />}
                    <TextLabel key={index} fontWeight={isHeader ? 'bold' : 'normal'}>{text}</TextLabel>
                </>
            ))}
        </Container>
    );
};

const Container = styled.View`
    width: 90%;
    padding: 10px 0;
    background-color: ${(props) => props.color};
    border-radius: 10px;
    margin: 5px 0;
    flex-direction: row;
    align-items: center;
`;

const Divider = styled.View`
    width: 0.5px;
    height: 100%;
    background-color: ${colors.border};
`;

const TextLabel = styled(Label)`
    flex: 1;
    font-weight: ${(props) => props.fontWeight};
    text-align: center;
`;
