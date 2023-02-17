import React from 'react';
import styled from 'styled-components/native';
import { ScrollView } from 'react-native';

/**
 * 좌우 스크롤 가능한 이미지 리스트를 보여주는 컴포넌트
 * @param {Array} sources - 이미지 소스 배열
 * @param {String?} width - 이미지 너비 (기본: 100px)
 * @param {String?} height - 이미지 높이 (기본: 100px)
 * @param {number?} containerMargin - 컨테이너 외부 여백 (단위 없이 숫자만) (기본: 5)
 * @param {String?} imageMargin - 이미지 간 여백 (기본: 5px)
 */
export default ScrollImageList = (props) => {
    const { sources, width = '100px', height = '100px', containerMargin = 5, imageMargin = '5px', ...others } = props;

    return (
        <ScrollView horizontal={true} contentContainerStyle={{ margin: containerMargin }} {...others}>
            {sources.map((source, index) => (
                <StyledImage
                    key={index}
                    source={source}
                    width={width}
                    height={height}
                    margin={imageMargin}
                ></StyledImage>
            ))}
        </ScrollView>
    );
};

const StyledImage = styled.Image`
    width: ${(props) => props.width};
    height: ${(props) => props.height};
    margin: ${(props) => props.margin};
    object-fit: cover;
`;
