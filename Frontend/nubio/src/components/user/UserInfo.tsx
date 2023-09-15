import { useState } from 'react';
import { Content, MyImg, MyInfoWrapper, Title } from "../../styles/SProfilePage";
import Swal from 'sweetalert2';


const UserInfo = () => {
    const user = process.env.PUBLIC_URL + "/assets/user.png";
    const [nickName, setNickName] = useState<string>('김민규');
    const [newNickName, setNewNickName] = useState<string>(nickName);
    const [isInputDisabled, setIsInputDisabled] = useState<boolean>(true);
    const handleChange = (value: string) => {
        setNewNickName(value);
    }
    const enableInput = () => {
        setIsInputDisabled(false);
    }
    const changeNickName = () => {
        Swal.fire({
            position: "center",
            title: "닉네임을 변경하시겠습니까?",
            text: "NUBIO",
            showConfirmButton: true,
            showCancelButton: true,
            confirmButtonText: "예",
            cancelButtonText: "아니요",
            color: "black",
        }).then((res) => {
            if (res.isConfirmed) {
                // "예"를 눌렀을 때만 새로운 닉네임으로 변경
                setNickName(newNickName);
                setIsInputDisabled(true);
            } else {
                // "아니요"를 눌렀을 때는 변경하지 않음
                setIsInputDisabled(true);
                setNewNickName(nickName);
            }
        })
        setIsInputDisabled(true);

    }

    return(
        <MyInfoWrapper>
            <MyImg src={user} alt="" />
            <div>
                <Title>이메일</Title>
                <Content>abc123@naver.com</Content>
            </div>
            <div>
                <Title>이름</Title>
                <Content>김민규</Content>
            </div>
            <div>
                <Title>닉네임</Title>
                <div style={{ justifyContent: 'space-between'}}>
                    <input type="text" value={newNickName} onChange={(e) => (handleChange(e.target.value))} disabled={isInputDisabled} />
                    <button onClick={isInputDisabled ? enableInput : changeNickName}>{isInputDisabled ? '수정' : '저장'}</button>
                </div>
            </div>
        </MyInfoWrapper>
    )
}

export default UserInfo;