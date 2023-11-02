import {
  ManIcon,
  Form,
  WomanIcon,
  GenderWrapper,
  SubmitButton,
} from "@styles/SSignUpPage";
import { useRef, MouseEvent, useState } from "react";
import useInput from "@/hooks/useInput";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { useEffect } from "react";

const SignUpForm = ({
  setIsLoading,
}: {
  setIsLoading: React.Dispatch<React.SetStateAction<boolean>>;
}) => {
  const [email, onChangeEmail] = useInput("");
  const [pwd, onChangePwd] = useInput("");
  const [pwdc, onChangePwdc] = useInput("");
  const [nickName, onChangeNickName] = useInput("");
  const [birth, onChangeBirth] = useInput("");
  const [gender, setGender] = useState<string>("MALE");
  const [emailConfirm, setEmailConfirm] = useState(false);
  const [nickNameCofirm, setNickNameConfirm] = useState(false);
  const [pwdSame, setPwdSame] = useState(false);
  const navigate = useNavigate();
  const [isConfirm, setIsConfirm] = useState(false);

  useEffect(() => {
    if (pwd === pwdc) {
      setPwdSame(true);
    } else {
      setPwdSame(false);
    }
  }, [pwdc]);

  const data = {
    email,
    password: pwd,
    passwordCheck: pwdc,
    nickname: nickName,
    birth,
    gender,
  };

  const signUp = async (e: any) => {
    e.preventDefault();
    await axios
      .post(process.env.REACT_APP_SERVER_URL + "/start/v1/member/signup", data)
      .then((res) => {
        // Todo : 회원가입 성공 Swal
        Swal.fire({
          title: "회원가입 성공",
          text: "Nubio",
          icon: "success",
        }).then((res) => {
          if (res.isConfirmed) {
            navigate("/login");
          }
        });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // 이메일 인증
  const EmailCertification = (e: any) => {
    e.preventDefault();
    setIsLoading(true);
    axios
      .post(process.env.REACT_APP_SERVER_URL + "/start/v1/email", { email })
      .then((res) => {
        setIsLoading(false);
        Swal.fire({
          title: "이메일 인증",
          input: "text",
          inputAttributes: {
            autocapitalize: "off",
          },
          showCancelButton: true,
          confirmButtonText: "확인",
          showLoaderOnConfirm: true,
          preConfirm: async (code) => {
            try {
              await axios.post(
                process.env.REACT_APP_SERVER_URL + "/start/v1/email/confirms",
                {
                  email,
                  code,
                },
              );
            } catch (err: any) {
              if (err.response.data.errorCode === "A-003") {
                Swal.fire({
                  title: "인증 실패",
                  icon: "error",
                  text: "NUBIO",
                });
                return;
              }
            }
          },
        }).then(() => {
          Swal.fire({
            title: "인증 성공",
            icon: "success",
            text: "NUBIO",
          });
          setEmailConfirm(true);
          setIsConfirm(true);
        });
      })
      .catch((err) => {
        if (err.response.data.errorCode === "M-009") {
          Swal.fire({
            title: "이미 존재하는 이메일입니다.",
            icon: "error",
            text: "NUBIO",
          });
        }
      });
  };
  // 닉네임 중복 확인
  const checkNickname = (e: any) => {
    e.preventDefault();
    axios
      .post(process.env.REACT_APP_SERVER_URL + "/start/v1/member/nickname", {
        nickname: nickName,
      })
      .then((res) => {
        if (res.data.data) {
          Swal.fire({
            title: "사용가능한 닉네임입니다.",
            icon: "success",
            text: "NUBIO",
          });
          setNickNameConfirm(true);
        } else {
          Swal.fire({
            title: "이미 사용 중인 닉네임입니다.",
            icon: "error",
            text: "NUBIO",
          });
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // 남자 아이콘
  const manUrl = process.env.PUBLIC_URL + "/assets/user/man.png";
  const [manCheck, setManCheck] = useState<boolean>(true);
  const manInputRef = useRef<HTMLInputElement | null>(null);
  const manId = manCheck ? "manCheck" : "manUncheck";
  const handleManIconClick = (event: MouseEvent<HTMLImageElement>) => {
    if (manInputRef.current) {
      manInputRef.current.click();
      setManCheck(true);
      setWomanCheck(false);
      setGender("MALE");
    }
  };

  // 여자 아이콘
  const womanUrl = process.env.PUBLIC_URL + "/assets/user/woman.png";
  const [womanCheck, setWomanCheck] = useState<boolean>(false);
  const womanInputRef = useRef<HTMLInputElement | null>(null);
  const womanId = womanCheck ? "womanCheck" : "womanUncheck";
  const handleWomanIconClick = (event: MouseEvent<HTMLImageElement>) => {
    if (womanInputRef.current) {
      womanInputRef.current.click();
      setWomanCheck(true);
      setManCheck(false);
      setGender("FEMALE");
    }
  };
  return (
    <Form>
      <span>
        <input
          type="email"
          placeholder="아이디(이메일 형식)"
          value={email}
          onChange={onChangeEmail}
        />
        <button
          id="checkEmail"
          onClick={EmailCertification}
          disabled={isConfirm ? true : false}
        >
          {isConfirm ? "인증완료" : "이메일 인증"}
        </button>
      </span>
      <span id="nickname">
        <input
          type="text"
          placeholder="닉네임"
          value={nickName}
          onChange={onChangeNickName}
          disabled={emailConfirm ? false : true}
        />
        <button id="checkNickname" onClick={(e) => checkNickname(e)}>
          중복확인
        </button>
      </span>
      <span>
        <input
          type="password"
          placeholder="비밀번호"
          value={pwd}
          onChange={onChangePwd}
          disabled={emailConfirm && nickNameCofirm ? false : true}
        />
      </span>
      <span>
        <input
          type="password"
          placeholder="비밀번호 확인"
          value={pwdc}
          onChange={onChangePwdc}
          disabled={emailConfirm && nickNameCofirm ? false : true}
        />
      </span>
      {!pwdc ? null : pwdSame ? (
        <p style={{ color: "green" }}>비밀번호가 일치합니다.</p>
      ) : (
        <p style={{ color: "red" }}>비밀번호가 일치하지 않습니다.</p>
      )}
      <span>
        <input
          type="date"
          id="date"
          value={birth}
          onChange={onChangeBirth}
          disabled={emailConfirm && nickNameCofirm ? false : true}
        />
      </span>
      <GenderWrapper>
        <ManIcon src={manUrl} onClick={handleManIconClick} id={manId} />
        <input type="checkbox" id="man" ref={manInputRef} />
        <WomanIcon src={womanUrl} onClick={handleWomanIconClick} id={womanId} />
        <input type="checkbox" id="woman" ref={womanInputRef} />
      </GenderWrapper>
      <SubmitButton type="submit" onClick={signUp}>
        회 원 가 입
      </SubmitButton>
    </Form>
  );
};

export default SignUpForm;
