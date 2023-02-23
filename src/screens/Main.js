import React from 'react';
import Button from '../components/atoms/Button';
import AnimatingButton from '../components/molecules/AnimatingButton';
import styled from 'styled-components/native';
import trashAnimation from '../../assets/anims/trash.json';
import clockAnimation from '../../assets/anims/clock.json';
import Notice from '../components/atoms/Notice';
import LoginContext from '../context/Login';
import { getUserInfo, signOut } from '../controllers/LoginController';
import LoadingContext from '../context/Loading';

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

const LoginContainer = styled.View`
    width: 100%;
    padding: 15px;
    background-color: ${colors.secondary};
    border-radius: 10px;
    margin: 10px 0;
`;

const Title = styled.Text`
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 10px;
`;

const Main = ({ navigation }) => {
    const [userName, setUsetName] = React.useState('사용자');
    const trashAnim = React.useRef(null);
    const clockAnim = React.useRef(null);
    const { isLogin, dispatch } = React.useContext(LoginContext);
    const { setIsLoading } = React.useContext(LoadingContext);

    React.useEffect(() => {
        trashAnim.current?.play();
        clockAnim.current?.play();
        setInterval(() => {
            trashAnim.current?.play();
            clockAnim.current?.play();
        }, 10000);
    }, []);

    React.useEffect(() => {
        if (isLogin) {
            getUserInfo().then((result) => {
                if (result.isSuccess()) {
                    const data = result.tryGetValue();
                    setUsetName(data.name);
                }
            });
        }
    }, [isLogin]);

    return (
        <Container>
            <LoginContainer>
                {isLogin ? <Title>안녕하세요 {userName}님</Title> : <Title>로그인이 필요합니다</Title>}
                <Button
                    title={isLogin ? '로그아웃' : '로그인'}
                    onPress={async () => {
                        if (isLogin) {
                            setIsLoading(true);
                            const result = await signOut();
                            if (result.isSuccess()) {
                                dispatch(false);
                                alert('로그아웃 되었습니다.');
                            } else {
                                alert('로그아웃에 실패했습니다.');
                            }
                            setIsLoading(false);
                        } else {
                            navigation.navigate('Email');
                        }
                    }}
                />
            </LoginContainer>
            <MenuContainer>
                <Row>
                    <MenuButton
                        title="나의 예약 현황"
                        animationSource={clockAnimation}
                        animationRef={clockAnim}
                        animationHeight="70px"
                        align="flex-start"
                        textAlign="left"
                        gap="20px"
                        onPress={() => {
                            if (isLogin) {
                                navigation.navigate('Reservation');
                            } else {
                                navigation.navigate('Email');
                            }
                        }}
                    />
                    <ButtonDivider />
                    <MenuButton
                        title="쓰레기 등록하기"
                        animationSource={trashAnimation}
                        animationRef={trashAnim}
                        align="flex-end"
                        textAlign="right"
                        onPress={() => {
                            if (isLogin) {
                                navigation.navigate('Register');
                            } else {
                                navigation.navigate('Email');
                            }
                        }}
                    />
                </Row>
                <NoticeArea />
            </MenuContainer>
        </Container>
    );
};

export default Main;
