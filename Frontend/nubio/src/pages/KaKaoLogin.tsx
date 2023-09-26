import { useEffect } from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
const KaKaoLogin = () => {
    const navigate = useNavigate();
    useEffect(() => {
        const code = new URL(window.location.href).searchParams.get('code');
        const redirectUrl = 'http://localhost:3000/oauth/kakao' 
        axios.post('https://nubi0.com/start/v1/oauth/kakao/callback', {code,redirectUrl})
             .then((res) => {
                const {accessToken, refreshToken, refreshTokenExpireTime} = res.data.data;
                console.log(accessToken);
                axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
                localStorage.setItem('refreshToken', refreshToken);
                localStorage.setItem('refreshTokenExpireTime', refreshTokenExpireTime);
                navigate('/')
             })
             .catch((err) => {
                console.error(err);
             })
    })
    return(<></>)
}

export default KaKaoLogin;