################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../Levels.cpp \
../Player.cpp \
../Question.cpp \
../Runner.cpp 

OBJS += \
./Levels.o \
./Player.o \
./Question.o \
./Runner.o 

CPP_DEPS += \
./Levels.d \
./Player.d \
./Question.d \
./Runner.d 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -I"../`gcc -print-prog-name=cc1plus` -v" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


