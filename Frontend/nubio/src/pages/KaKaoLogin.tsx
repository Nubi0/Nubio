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
                localStorage.setItem('refresh_token', res.data.refresh_token);
                localStorage.setItem('refresh_token_expiration_time', res.data.refresh_token_expiration_time);
                navigate('/')
             })
             .catch((err) => {
                console.error(err);
             })
    })
    return(<></>)
}

export default KaKaoLogin;