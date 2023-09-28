import axios from 'axios';
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setTaste } from "../../../redux/slice/SignUpSlice";
import {
  PrefrenceModalBox,
  PrefrenceModalOverlay,
} from "../../../styles/SSignUpPage";
import DrinkList from "./DrinkList";
import EatList from "./EatList";
import PlayList from "./PlayList";

type SetPrefrenceModalProps = {
  closeModal: () => void;
};

const SetPrefrenceModal: React.FC<SetPrefrenceModalProps> = ({
  closeModal,
}) => {
  const dispatch = useDispatch();
  const [selectedImages, setSelectedImages] = useState<string[]>([]);
  const taste = useSelector((state: any) => state.signup.taste);

  const handleImageClick = (name: string, type: string) => {
    const isSelected = selectedImages.includes(name);
    const action = {name, type}
    if (isSelected) {
      setSelectedImages(selectedImages.filter((image) => image !== name));
      dispatch(setTaste(action))

    } else {
      setSelectedImages([...selectedImages, name]);
      dispatch(setTaste(action))
    }
  };

  const handleSave = () => {
    axios.post('https://nubi0.com/enjoy/v1/enjoy/profile/taste', {taste})
          .then((res) => {
            console.log(res.data.data)
            closeModal();
          })
          .catch((err) => {
            console.error(err);
          })
  }

  useEffect(() => {
    axios.get('https://nubi0.com/enjoy/v1/enjoy/profile/taste')
          .then((res) => {
            console.log(res.data);
            const taste = res.data.data.taste;
            const tmp: any[] = []
            taste.map((value: any) => {
                const action = {name: value.type, type: value.detail_type};
                dispatch(setTaste(action));
                value.detail_type.map((value: any) => {
                  tmp.push(value);
                })
              }
            ) 
            setSelectedImages(tmp);
          })
          .catch((err) => {
            console.error(err);
          })
    console.log(selectedImages)
  }, [])

  return (
    <PrefrenceModalOverlay>
      <PrefrenceModalBox>
        <DrinkList
          handleImageClick={handleImageClick}
          selectedImages={selectedImages}
        />
        <EatList
          handleImageClick={handleImageClick}
          selectedImages={selectedImages}
        />
        <PlayList
          handleImageClick={handleImageClick}
          selectedImages={selectedImages}
        />
        <button onClick={handleSave}>닫기</button>
      </PrefrenceModalBox>
    </PrefrenceModalOverlay>
  );
};

export default SetPrefrenceModal;
