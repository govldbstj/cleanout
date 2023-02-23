import React from 'react';
import styled from 'styled-components/native';
import { Image, Pressable } from 'react-native';

/**
 * 이미지 버튼 atom
 * 필요한 경우 props 추가해서 사용
 * @param {string} source 버튼에 들어갈 이미지
 * @param {function} onPress 버튼 클릭 시 실행할 함수
 * @param {string?} width 버튼 너비(기본: auto)
 * @param {string?} height 버튼 높이(기본: auto)
 */
export default ImageButton = (props) => {
    const { source, onPress, width = 'auto', height = 'auto', ...others } = props;

    return (
        <ImagePressable width={width} height={height} onPress={onPress} {...others}>
            <StyledImage width={width} height={height} source={source} />
        </ImagePressable>
    );
};

const ImagePressable = styled.Pressable`
    width: ${(props) => props.width};
    height: ${(props) => props.height};
    flex-direction: row;
    flex-wrap: wrap;
`;

const StyledImage = styled.Image`
    width: ${(props) => props.width};
    height: ${(props) => props.height};
    border-radius: 5px;
    resize-mode: cover;
    margin: 0px;
`;
