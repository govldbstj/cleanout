import React from 'react';
import styled from 'styled-components/native';
import LottieView from 'lottie-react-native';
import colors from '../../styles/colors';

/**
 * Lottie 애니메이션을 포함하는 버튼 컴포넌트
 * @param {string} title 버튼에 들어갈 글자
 * @param {function} onPress 버튼 클릭 시 실행할 함수
 * @param {String} animationSource Lottie 애니메이션 파일 경로
 * @param {object?} animationRef Lottie 애니메이션 ref(기본: null)
 * @param {string?} animationHeight Lottie 애니메이션 높이(기본: 100px)
 * @param {string?} backgroundColor 버튼 배경색(기본: colors.secondary)
 * @param {string?} fontSize 버튼 글자 크기(기본: 17px)
 * @param {string?} align 버튼 내부 요소 정렬(기본: center)
 * @param {string?} textAlign 버튼 글자 정렬(기본: center)
 * @param {string?} gap 버튼 내부 요소 간격(기본: 0px)
 */
export default AnimatingButton = (props) => {
    const {
        title,
        onPress,
        animationSource,
        animationRef = null,
        animationHeight = '100px',
        backgroundColor = colors.secondary,
        fontSize = '17px',
        align = 'center',
        textAlign = 'center',
        gap = '0px',
        ...others
    } = props;

    return (
        <BoxPressable onPress={onPress} backgroundColor={backgroundColor} align={align} {...others}>
            <ButtonLabel fontSize={fontSize} align={textAlign} gap={gap}>
                {title}
            </ButtonLabel>
            <LottieAnimation ref={animationRef} source={animationSource} height={animationHeight} loop={false} autoPlay={false}/>
        </BoxPressable>
    );
};

const BoxPressable = styled.Pressable`
    padding: 15px 15px 0px 15px;
    background-color: ${(props) => props.backgroundColor};
    border-radius: 10px;
    margin-top: 10px;
    align-items: ${(props) => props.align};
`;

const ButtonLabel = styled.Text`
    margin-bottom: ${(props) => props.gap};
    font-size: ${(props) => props.fontSize};
    font-weight: bold;
    text-align: ${(props) => props.align};
`;

const LottieAnimation = styled(LottieView)`
    height: ${(props) => props.height};
`;
