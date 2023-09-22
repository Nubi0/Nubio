import { createSlice } from '@reduxjs/toolkit';

const ProfileSlice = createSlice({
  name: 'profileSlice',
  initialState: {
    isChange: false,
    newNickName: '',
    isInputDisabled: true,
    email: null,
    profileUrl: null,
    birth: null,
    gender: null,
  },
  reducers: {
    setIsChange: (state, action) => {
        state.isChange = action.payload;
    },
    setNewNickName: (state, action) => {
        state.newNickName = action.payload;
    },
    setIsInputDisabled: (state, action) => {
        state.isInputDisabled = action.payload;
    },
    setEmail: (state, action) => {
      state.email = action.payload;
    },
    setProfileUrl: (state, action) => {
      state.profileUrl = action.payload;
    },
    setBirth: (state, action) => {
      state.birth = action.payload;
    },
    setGender: (state, action) => {
      state.gender = action.payload;
    }
  },
});

export const { setIsChange,  setNewNickName, setIsInputDisabled, setEmail, setProfileUrl, setBirth, setGender } = ProfileSlice.actions;
export default ProfileSlice.reducer;