import React from 'react';
import Button from '../components/atoms/Button';
import AnimatingButton from '../components/molecules/AnimatingButton';
import styled from 'styled-components/native';
import trashAnimation from '../../assets/anims/trash.json';
import clockAnimation from '../../assets/anims/clock.json';
import Notice from '../components/atoms/Notice';

const Container = styled.View`
    flex: 1;
    margin: 0 20px;
    align-items: flex-end;
`;

const Row = styled.View`
    flex-direction: row;
`;

const MenuContainer = styled.View`
    flex: 1;
    width: 100%;
    align-items: center;
    justify-content: center;
`;

const MenuButton = styled(AnimatingButton)`
    flex: 1;
`;

const ButtonDivider = styled.View`
    width: 20px;
`;

const NoticeArea = styled(Notice)`
    width: 100%;
    margin-top: 20px;
`;

const Main = ({ navigation }) => {
    const trashAnim = React.useRef(null);
    const clockAnim = React.useRef(null);

    React.useEffect(() => {
        trashAnim.current?.play();
        clockAnim.current?.play();
        setInterval(() => {
            trashAnim.current?.play();
            clockAnim.current?.play();
        }, 10000);
    }, []);

    return (
        <Container>
            <Button title="로그인" onPress={() => navigation.navigate('Login')} />
            <MenuContainer>
                <Row>
                    <MenuButton
                        title="나의 예약 현황"
                        animationSource={clockAnimation}
                        animationRef={clockAnim}
                        align="flex-start"
                        onPress={() => navigation.navigate('Reservation')}
                    />
                    <ButtonDivider />
                    <MenuButton
                        title="쓰레기 등록하기"
                        animationSource={trashAnimation}
                        animationRef={trashAnim}
                        align="flex-end"
                        gap="5px"
                        onPress={() => navigation.navigate('Register')}
                    />
                </Row>
                <NoticeArea />
            </MenuContainer>
        </Container>
    );
};

export default Main;
