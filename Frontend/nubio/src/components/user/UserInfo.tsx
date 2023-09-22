import { useRef, useEffect, useState } from 'react';
import { Content, MyInfoWrapper, Title } from "../../styles/SProfilePage";
import UserImg from './UserImg';
import { useDispatch, useSelector } from 'react-redux';
import { setNewNickName, setIsInputDisabled, setIsChange } from '../../redux/slice/Profileslice';

const UserInfo = () => {
    const inputRef = useRef<HTMLInputElement | null>(null);
    const newNickName = useSelector((state: any) => state.profile.newNickName);
    const isInputDisabled = useSelector((state: any) => state.profile.isInputDisabled);
    const email = useSelector((state: any) => state.profile.email);
    const dispatch = useDispatch();

    const handleChange = (value: string) => {
        dispatch(setNewNickName(value));
    }

    const enableInput = () => {
        dispatch(setIsInputDisabled(false));
        dispatch(setIsChange(true));
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
