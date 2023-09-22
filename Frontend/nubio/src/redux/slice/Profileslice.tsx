import { createSlice } from '@reduxjs/toolkit';

const ProfileSlice = createSlice({
  name: 'profileSlice',
  initialState: {
    isChange: false,
    newNickName: '',
    isInputDisabled: true,
    email: null,
    profileUrl: null,
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
    }
  },
});

export const { setIsChange,  setNewNickName, setIsInputDisabled, setEmail, setProfileUrl  } = ProfileSlice.actions;
export default ProfileSlice.reducer;