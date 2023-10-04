import { useRef, useState, useEffect } from 'react';
import { ImgPlus, MyImg, ProfileImg } from "../../styles/SProfilePage";
import { useDispatch, useSelector } from 'react-redux';
import { setProfileUrl } from '../../redux/slice/Profileslice';

const UserImg = ({setIsChange}: any) => {
    const user = process.env.PUBLIC_URL + "/assets/user.png";
    const plus = process.env.PUBLIC_URL + '/assets/camera.svg';
    const profileUrl = useSelector((state: any) => state.profile.profileUrl);
    const [image, setImage] = useState(user);
    const [prevImage, setPrevImage] = useState(user);
    const dispatch = useDispatch();

    const fileInput = useRef<any>(null);
    const onChange = (e: any) => {
        console.log(e.target.files);
        if(e.target.files.length > 0){
            console.log(e.target.files.length)
            const file = e.target.files[0];
            const imgUrl = URL.createObjectURL(file);
            dispatch(setProfileUrl(imgUrl));
            
        } else {
            dispatch(setProfileUrl(prevImage));
        }
    }

    // 이미지 클릭으로 input 태그 클릭
    const handlePlus = () => {
        fileInput.current?.click();
    }


    useEffect(() => {
        // 이미지가 변경되었을 때만 dispatch
        if (image !== prevImage) {
            dispatch(setIsChange(true));
            setPrevImage(image);
        }
    }, [image, prevImage, dispatch, setIsChange]);

    return(
        <ProfileImg>
            <MyImg src={profileUrl} alt="" />
            <ImgPlus src={plus} onClick={handlePlus} />
            <input 
                type="file" 
                style={{display:'none'}}
                accept='image/jpg,image/png,image/jpeg'
                name='profile_img'
                onChange={(e) => onChange(e)}
                ref={fileInput} />
        </ProfileImg>
    )
}

export default UserImg;