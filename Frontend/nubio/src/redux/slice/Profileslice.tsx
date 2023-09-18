import { createSlice } from '@reduxjs/toolkit';

const ProfileSlice = createSlice({
  name: 'enjoyslice',
  initialState: {
    isChange: false,
    nickName: '김민규',
    newNickName: '김민규',
    isInputDisabled: true,
  },
  reducers: {
    setIsChange: (state, action) => {
        state.isChange = action.payload;
    },
    setNickName: (state, action) => {
        state.nickName = action.payload;
    },
    setNewNickName: (state, action) => {
        state.newNickName = action.payload;
    },
    setIsInputDisabled: (state, action) => {
        state.isInputDisabled = action.payload;
    }
  },
});

export const { setIsChange, setNickName, setNewNickName, setIsInputDisabled } = ProfileSlice.actions;
export default ProfileSlice.reducer;