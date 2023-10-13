import { useRef, useEffect, useState } from 'react';
import { Content, MyInfoWrapper, Title, GenderWrapper, ManIcon, WomanIcon } from "../../styles/SProfilePage";
import UserImg from './UserImg';
import { useDispatch, useSelector } from 'react-redux';
import { setNewNickName, setIsInputDisabled, setIsChange, setBirth, setGender } from '../../redux/slice/Profileslice';
import axios from 'axios';
import Swal from 'sweetalert2';

const UserInfo = ({setFile, setNickCheck}: any) => {
    const inputRef = useRef<HTMLInputElement | null>(null);
    const newNickName = useSelector((state: {profile: {newNickName: string}}) => state.profile.newNickName);
    const isInputDisabled = useSelector((state: {profile: {isInputDisabled: boolean}}) => state.profile.isInputDisabled);
    const email = useSelector((state: any) => state.profile.email);
    const birth = useSelector((state: any) => state.profile.birth);
    const gender = useSelector((state: any) => state.profile.gender);
    const dispatch = useDispatch();

    const handleChange = (value: string) => {
        dispatch(setNewNickName(value));
    }

    const enableInput = () => {
        dispatch(setIsInputDisabled(false));
        dispatch(setIsChange(true));
    }

    const handleBirth = (e: any) => {
        dispatch(setBirth(e.target.value));
        dispatch(setIsChange(true));
    }

  const [manCheck, setManCheck] = useState<boolean>(false);
  const [womanCheck, setWomanCheck] = useState<boolean>(false);

  // 남자 아이콘
  const manUrl = process.env.PUBLIC_URL + '/assets/man.png';
  const manInputRef = useRef<HTMLInputElement | null>(null);
  const manId = manCheck ? 'manCheck' : 'manUncheck';
  const handleManIconClick = () => {
    if (manInputRef.current) {
      manInputRef.current.click();
      setManCheck(true);
      setWomanCheck(false);
      dispatch(setGender('male'));
      dispatch(setIsChange(true));
    }
  };

  // 여자 아이콘
  const womanUrl = process.env.PUBLIC_URL + '/assets/woman.png';
  const womanInputRef = useRef<HTMLInputElement | null>(null);
  const womanId = womanCheck ? 'womanCheck' : 'womanUncheck';
  const handleWomanIconClick = () => {
    if (womanInputRef.current) {
      womanInputRef.current.click();
      setWomanCheck(true);
      setManCheck(false);
      dispatch(setGender('female'));
      dispatch(setIsChange(true));
    }
  };

  const doubleCheck = () => {
    axios
        .post(process.env.REACT_APP_SERVER_URL + '/start/v1/member/nickname', {nickname: newNickName})
        .then((res) => {
            if(res.data.data){
                Swal.fire({
                    title: '사용가능한 닉네임입니다.',
                    icon: 'success',
                    text: 'NUBIO',
                })
                setNickCheck(true);
            } else {
                Swal.fire({
                    title: '중복된 닉네임입니다.',
                    icon: 'error',
                    text: 'NUBIO',
                })
                setNickCheck(false);
            }
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
        if(gender === 'male') {
            setManCheck(true);
          } else {
            setWomanCheck(true);
          }
    }, [isInputDisabled]);

    return (
        <MyInfoWrapper>
            <UserImg setIsChange={setIsChange} setFile={setFile} />
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
                    <button onClick={isInputDisabled ? enableInput :doubleCheck}>{isInputDisabled ? '수정' : '중복확인'}</button>
                </div>
            </div>
            <div>
                <Title>생년월일</Title>
                <input type="date" value={birth} onChange={handleBirth} />
            </div>
            <GenderWrapper>
                <Title>성별</Title>
                <ManIcon src={manUrl} onClick={handleManIconClick} id={manId} />
                <input type="checkbox" id="man" ref={manInputRef} />
                <WomanIcon src={womanUrl} onClick={handleWomanIconClick} id={womanId} />
                <input type="checkbox" id="woman" ref={womanInputRef} />
            </GenderWrapper>
        </MyInfoWrapper>
    )
}

export default UserInfo;
