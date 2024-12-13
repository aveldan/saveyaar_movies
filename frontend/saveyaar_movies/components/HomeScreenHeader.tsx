import React from "react";
import Animated from "react-native-reanimated";
import { Image, Pressable, StyleSheet, View } from "react-native";

import { NotificationIcon } from "@/assets/svg";
import Scale from "@/constants/Scale";

interface HeaderProps {
    handleProfile?: () => void,
    handleNotification?: () => void,
}

export default function HomeScreenHeader({handleProfile, handleNotification}: HeaderProps) {
    
    const userImageSource = "https://s3-alpha-sig.figma.com/img/afb9/3a50/98d2fa0a32dceb793f76f36a8d18243c?Expires=1734912000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=n9avdl84noC9tNUHjPMqyatvVvO-p5lQ5rhH5Iyk2CXAGZ2HJA723W4k5V7sNTRsixM5tiVcNtI~ZBh6M6VFhIadetul~9ERc8ebBzgXYUDcymiynE4EMD0NVPs3UhJgFt9YXq7bEBMftDglqfE9hqf816BnnFgtjVcKSem-whEsClxU7S3Ud8eA768L0lSQZXziYEXEcGFbWK7okIwFy9MUhuJCxcS7YkTgXyo6izj1aSg-8rnqu~l6Df6Qw7XJfobDQG3Qz~G2oF1i5iiq-J7y1EgSc4XeocFL7mWOEuoZEhszyA~yIKK8xZfRV7e5dAORJ3Zg0oPA2NGWhSW6BQ__";
    
    return (
        <View style={styles.headerTitleContainer}>
            <Pressable
                style={styles.profileBorder}
                onPress={handleProfile}
            >
                <Image style={styles.profileImg} source={{uri: userImageSource}}/>
            </Pressable>
            <Pressable onPress={handleNotification}>
                <NotificationIcon width={28} height={28} />
            </Pressable>
        </View>
    )
}

const styles = StyleSheet.create({
    headerTitleContainer: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        paddingHorizontal: 16,
    },
    profileBorder: {
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: '#9C7CDE',
        height: Scale(44),
        width: Scale(44),
        borderRadius: Scale(22),
    },
    profileImg: {
        height: Scale(40),
        width: Scale(40),
        borderRadius: Scale(20),
    },
});