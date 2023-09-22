import { useRef, useEffect, useState } from 'react';
import { Content, MyInfoWrapper, Title } from "../../styles/SProfilePage";
import UserImg from './UserImg';
import { useDispatch, useSelector } from 'react-redux';
import { setNewNickName, setIsInputDisabled, setIsChange } from '../../redux/slice/Profileslice';
import axios from 'axios';

const UserInfo = () => {
    const inputRef = useRef<HTMLInputElement | null>(null);
    const newNickName = useSelector((state: any) => state.profile.newNickName);
    const isInputDisabled = useSelector((state: any) => state.profile.isInputDisabled);
    const email = useSelector((state: any) => state.profile.email);
    const [waiting, setWaiting] = useState(false);
    const [isConfirm, setIsConfirm] = useState(false);
    const dispatch = useDispatch();

    const handleChange = (value: string) => {
        dispatch(setNewNickName(value));
    }

    const enableInput = () => {
        dispatch(setIsInputDisabled(false));
        dispatch(setIsChange(true));
    }

    const EmailCertification = async (e: any) => {
        e.preventDefault();
        setWaiting(true);
        await axios.post(process.env.REACT_APP_SERVER_URL + '/start/v1/email')
                    .then((res) => {
                        console.log(res);
                    })
                    .catch((err) => {
                        console.error(err);
                    })
    }

    const confirmEmail = async (e: any) => {
        e.preventDefault();
        await axios.post(process.env.REACT_APP_SERVER_URL + '/start/v1/email/confirms')
                    .then((res) => {
                        console.log(res);
                        setIsConfirm(true);
                    })
                    .catch((err) => {
                        console.log(err);
                    })
    }

    useEffect(() => {
        // isInputDisabled 값이 변경될 때 포커스를 설정
        if (!isInputDisabled && inputRef.current) {
            inputRef.current.focus();
        }
    }, [isInputDisabled]);

    return (
        <MyInfoWrapper>
            <UserImg setIsChange={setIsChange} />
            <div>
                <Title>이메일</Title>
                <Content>{email}</Content>
                {isConfirm ? '인증완료' : <button onClick={ waiting ? confirmEmail : EmailCertification}>{waiting ? '이메일확인' : '이메일인증'}</button>}
            </div>
            <div>
                <Title>닉네임</Title>
                <div style={{ justifyContent: 'space-between' }}>
                    <input
                        ref={inputRef}
                        type="text"
                        value={newNickName}
                        onChange={(e) => (handleChange(e.target.value))}
                        disabled={isInputDisabled}
                    />
                    <button onClick={isInputDisabled ? enableInput : () => {}}>{isInputDisabled ? '수정' : null}</button>
                </div>
            </div>
        </MyInfoWrapper>
    )
}

export default UserInfo;
