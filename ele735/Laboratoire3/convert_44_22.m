function [audio_22khz] = convert_44_22(audio_stereo_44)
    mono44 = (audio_stereo_44(:,1) + audio_stereo_44(:,2)) / 2;
    audio_22khz = downsample(mono44, 2);
end