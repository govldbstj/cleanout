import React from 'react';
import styled from 'styled-components/native';
import { ScrollView, View } from 'react-native';

/**
 * 좌우 스크롤 가능한 이미지 리스트를 보여주는 컴포넌트
 * @param {Array} sources - 이미지 Uri 배열
 * @param {string?} width - 이미지 너비 (기본: 100px)
 * @param {string?} height - 이미지 높이 (기본: 100px)
 * @param {string?} containePadding - 컨테이너 내부 여백 (기본: 5%)
 * @param {string?} imageSpaceWidth - 이미지가 차지하는 너비 (기본: 110px)
 */
export default ScrollImageList = (props) => {
    const {
        sources,
        width = '100px',
        height = '100px',
        containerPadding = '5%',
        imageSpaceWidth = '110px',
        ...others
    } = props;

    return (
        <ScrollView contentContainerStyle={{ padding: containerPadding }} horizontal={true} {...others}>
            {sources.map((source, index) => (
                <ImageContainer key={index} width={imageSpaceWidth}>
                    <StyledImage source={{ uri: source }} width={width} height={height}></StyledImage>
                </ImageContainer>
            ))}
        </ScrollView>
    );
};

const StyledImage = styled.Image`
    width: ${(props) => props.width};
    height: ${(props) => props.height};
    object-fit: cover;
`;

const ImageContainer = styled.View`
    width: ${(props) => props.width};
    justify-content: center;
    align-items: center;
`;
