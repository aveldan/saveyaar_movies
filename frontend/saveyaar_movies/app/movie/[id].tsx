import { useLocalSearchParams } from "expo-router";
import React, { useEffect, useState } from "react";
import { StyleSheet, View, Image } from "react-native";
import { useSafeAreaInsets } from "react-native-safe-area-context";

import { BellIconPurple, DotIconPurple, HeartIconPurple, ShareIconPurple } from "@/assets/svg";
import TextComponent from "@/components/TextComponents";
import { IMAGE_URL, MOVIE_URL, TMDB_KEY } from "@env";
import Scale from "@/constants/Scale";
import { LinearGradient } from "expo-linear-gradient";

interface TwoLineText {
    textTop: string,
    textBottom: string
};

const TwoLineText = ({textTop, textBottom}: TwoLineText) => {
    return (
        <View style={styles.two_line_container}>
            <TextComponent
                text={textTop}
                size={8}
                numberOfLine={1}
                color="#FFFFFF"
            />
            <TextComponent
                text={textBottom}
                size={12}
                numberOfLine={1}
                color="#FFFFFF"
            />
        </View>
    )
};

export default function MovieScreen() {
    const { id } = useLocalSearchParams();

    const [movie, setMovie] = useState<any>(null);
    const [imageData, setImageData] = useState<string | null>(null);
    const [duration, setDuration] = useState<string | null>(null);
    const [languages, setLanguages] = useState<any[] | null>(null);
    const [rating, setRating] = useState<string | null>(null);

    useEffect(()=> {
        const fetchImage = async (backdrop_path: string) => {
            try {
                const response = await fetch(IMAGE_URL+backdrop_path, {
                  headers: {
                    Authorization: `Bearer ${TMDB_KEY}`,
                  },
                });
                const blob = await response.blob();
                const reader = new FileReader();

                reader.onloadend = () => {
                    if (typeof reader.result === 'string') {
                        setImageData(reader.result);
                    } else {
                        console.error('Expected string from FileReader, got:', typeof reader.result);
                    }
                };
                
                reader.readAsDataURL(blob);
            } catch (error) {
                console.error('Error fetching image:', error);
            }
        };

        const calc_runtime = (runtime: number) => {
            const hours = Math.floor(runtime / 60);
            const minutes = runtime % 60;
            
            if(hours > 0 && minutes > 0){
                return `${hours}h ${minutes}min`;
            } else if(hours > 0) {
                return `${hours}h`
            } else if(minutes > 0) {
                return `${minutes}min`
            } else {
                return 'N/A'
            }
        };

        const filter_indian_languages = (languages: any[]) => {
            let filtered_langues: any[] = [];
            languages.forEach(language => {
                if(language.iso_3166_1 === 'IN'){
                    filtered_langues.push(language);
                }
            });

            return filtered_langues;
        };

        const fetchMovie = async () => {
            const url = new URL(`${MOVIE_URL}${id}`);
            url.searchParams.append('append_to_response', 'release_dates,translations,watch/providers');
            
            console.log(url.toString());

            const response = await fetch(url.toString(), {
                headers: {
                  Authorization: `Bearer ${TMDB_KEY}`,
                },
            });

            if(response.status === 200) {
                const movieData = await response.json();
                setMovie(movieData);
                setDuration(calc_runtime(movieData.runtime | 0));
                setLanguages(filter_indian_languages(movieData.translations.translations));
                // setRating(movieData.rating.toString());
                fetchImage(movieData.backdrop_path);
            } else {
                console.error(`Error: Received status code ${response.status} when trying to access movie id:${id}`);
            }
        };
        
        fetchMovie();
    }, [id]);

    const insets = useSafeAreaInsets();
    // console.log(movie);

    return (
        <View style={[styles.container, {paddingTop: insets.top+5}]}>
            {movie ? (
                <View>
                    {imageData && 
                        <View style={styles.image_container}>
                            <Image
                                source={{ uri: imageData }}
                                style={styles.backdrop}
                            />
                        </View>
                    }
                    <View style={styles.title}>
                        <TextComponent
                            text={movie.title}
                            size={16}
                            numberOfLine={2}
                            bold                
                        />
                        <View style={styles.icons}>
                            <BellIconPurple />
                            <HeartIconPurple />
                            <ShareIconPurple />
                        </View>
                    </View>
                    <View style={styles.color_content}>
                        <LinearGradient
                            colors={["#401D85", "#3D103D"]}
                            start={{ x: 0, y: 0 }}
                            end={{ x: 1, y: 0 }}
                            style={[StyleSheet.absoluteFill, {borderRadius: 8}]}
                        />
                        <TwoLineText textTop="Rating" textBottom={rating? rating : 'N/A'} />
                        <TwoLineText textTop="Release date" textBottom={movie.release_date || 'N/A'} />
                        <TwoLineText textTop="Duration" textBottom={duration || 'N/A'} />
                        <TwoLineText textTop="Languages" textBottom={languages?.length.toString() || 'N/A'} />
                    </View>
                    <View style={styles.ott}>
                        <DotIconPurple />
                        <TextComponent
                            text="Available on"
                            size={12}
                            style={{paddingLeft: 10}}
                        />
                    </View>
                    <TextComponent
                        text={movie.overview}
                        numberOfLine={10}
                        style={{paddingTop: 10}}
                        color="#FFFFFF99"
                    />
                </View>
            ) : (
                <TextComponent text="Loading..." size={16} />
            )}
        </View>        
    )
};


const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: "#151515",
        paddingHorizontal: 20,
    },
    title: {
        flexDirection: 'row',
        alignContent: 'center',
        justifyContent: 'space-between',
        paddingVertical: 20,
    },
    icons: {
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'space-between',
        width: 96,
    },
    backdrop: {
        borderRadius: 14,
        width: Scale(380),
        height: 200
    },
    image_container: {
        alignItems: 'center',
    },
    color_content: {
        height: Scale(67),
        justifyContent: 'space-evenly',
        alignItems: 'center',
        // alignContent: 'space-between',
        flexDirection: 'row'
    },
    ott: {
        flexDirection: 'row',
        paddingTop: 20,
        paddingBottom: 10,
        alignItems: 'center',
        alignContent: 'center',
    },
    two_line_container: {
        flexDirection: 'column',
        alignItems: 'center'
    }
});