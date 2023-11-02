import { useRef, useState, useEffect } from "react";
import { ImgPlus, MyImg, ProfileImg } from "@styles/SProfilePage";
import { useDispatch, useSelector } from "react-redux";
import { setProfileUrl, setIsChange } from "@redux/slice/Profileslice";

const UserImg = ({
  setFile,
}: {
  setFile: React.Dispatch<React.SetStateAction<File | null>>;
}) => {
  const user = process.env.PUBLIC_URL + "/assets/user/user.png";
  const plus = process.env.PUBLIC_URL + "/assets/user/camera.svg";
  const profile = useSelector(
    (state: { profile: { profileUrl: string } }) => state.profile.profileUrl,
  );
  const [image, setImage] = useState(user);
  const [prevImage, setPrevImage] = useState(user);
  const dispatch = useDispatch();

  const fileInput = useRef<HTMLInputElement | null>(null);
  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files.length > 0) {
      const file = e.target.files[0];
      const reader = new FileReader();
      setFile(file);
      reader.onload = () => {
        const dataURL = reader.result as string;
        dispatch(setProfileUrl(dataURL));
        dispatch(setIsChange(true));
      };
      reader.readAsDataURL(file);
    } else {
      dispatch(setProfileUrl(prevImage));
      dispatch(setIsChange(false));
    }
  };

  // 이미지 클릭으로 input 태그 클릭
  const handlePlus = () => {
    fileInput.current?.click();
  };

  useEffect(() => {
    // 이미지가 변경되었을 때만 dispatch
    if (image !== prevImage) {
      dispatch(setIsChange(true));
      setPrevImage(image);
    }
  }, [image, prevImage, dispatch, setIsChange]);

  return (
    <ProfileImg>
      <MyImg src={profile} alt="" />
      <ImgPlus src={plus} onClick={handlePlus} />
      <input
        type="file"
        style={{ display: "none" }}
        accept="image/jpg,image/png,image/jpeg"
        name="profile_img"
        onChange={(e) => onChange(e)}
        ref={fileInput}
      />
    </ProfileImg>
  );
};

export default UserImg;
