import { useState } from "react";
import {
  PrefrenceModalBox,
  PrefrenceModalOverlay,
} from "../../../styles/SSignUpPage";
import DrinkList from "./DrinkList";
import EatList from "./EatList";
import PlayList from "./PlayList";
import { useDispatch } from "react-redux";
import { setTaste } from "../../../redux/slice/SignUpSlice";

type SetPrefrenceModalProps = {
  closeModal: () => void;
};

const SetPrefrenceModal: React.FC<SetPrefrenceModalProps> = ({
  closeModal,
}) => {
  const dispatch = useDispatch();
  const [selectedImages, setSelectedImages] = useState<string[]>([]);
  const handleImageClick = (name: string, type: string) => {
    const isSelected = selectedImages.includes(name);
    const action = {name, type}
    if (isSelected) {
      setSelectedImages(selectedImages.filter((image) => image !== name));
    } else {
      setSelectedImages([...selectedImages, name]);
      dispatch(setTaste(action))
    }
  };
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
        <button onClick={closeModal}>닫기</button>
      </PrefrenceModalBox>
    </PrefrenceModalOverlay>
  );
};

export default SetPrefrenceModal;
