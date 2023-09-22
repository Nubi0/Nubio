import { useNavigate } from 'react-router-dom';
import { LoginPageWrapper, LoginForm, KakaoLogin } from '../styles/SLoginPage';
import { LoginLogo } from '../styles/SSignUpPage';
import useInput from '../hooks/useInput';
import axios from 'axios';

const LoginPage = () => {
  const logoUrl = process.env.PUBLIC_URL + '/assets/nubio.png';
  const navigate = useNavigate();
  const [id, onChangeId] = useInput('');
  const [pwd, onChanagePwd] = useInput('');

  const config = {
    email: id,
    password: pwd,
  };
  const login = async (e: any) => {
    e.preventDefault();
    await axios
      .post('https://nubi0.com/start/v1/member/login', config)
      .then((res) => {
        const { accessToken, refreshToken, refreshTokenExpireTime } = res.data.data;
        axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
        localStorage.setItem('refreshToken', refreshToken);
        localStorage.setItem('refreshTokenExpireTime', refreshTokenExpireTime);
        navigate('/enjoy');
      })
      .catch((err) => {
        console.error(err);
      });
  };
  const redirect_uri = 'http://localhost:3000/oauth/kakao';
  const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_KAKAO_LOGIN}&redirect_uri=${redirect_uri}&response_type=code`;
  const kakaoLogin = () => {
    window.location.href = kakaoURL;
  };

  return (
    <LoginPageWrapper>
      <LoginLogo src={logoUrl} onClick={() => navigate('/')} />
      <LoginForm>
        <input
          type="email"
          name="id"
          placeholder="아이디(이메일 형식)"
          onChange={onChangeId}
        />
        <input type="password" name="pw" placeholder="비밀번호" onChange={onChanagePwd} />
        <button type="submit" id="login" onClick={login}>
          로그인
        </button>
        <button id="text" onClick={() => navigate('/signUp')}>
          회원이 아니신가요?
        </button>
        <KakaoLogin
          src={process.env.PUBLIC_URL + '/assets/kakaologin.png'}
          onClick={kakaoLogin}
        />
      </LoginForm>
    </LoginPageWrapper>
  );
};

export default LoginPage;
